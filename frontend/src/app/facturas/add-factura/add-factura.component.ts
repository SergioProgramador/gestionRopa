import { Component, OnInit } from '@angular/core';
import { Factura } from '../factura';
import { ClientesService } from 'src/app/clientes/clientes.service';
import { ActivatedRoute, Router } from '@angular/router';
import {map, flatMap} from 'rxjs/operators';
import { FormControl } from '@angular/forms';
import { Observable } from 'rxjs';
import { FacturasService } from '../facturas.service';
import { Producto } from 'src/app/productos/producto';
import { MatAutocompleteSelectedEvent } from '@angular/material';
import { LineaFactura } from 'src/app/lineas-facturas/lineaFactura';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-add-factura',
  templateUrl: './add-factura.component.html',
  styleUrls: ['./add-factura.component.scss']
})
export class AddFacturaComponent implements OnInit {

  factura : Factura;
  

  busquedaDinamica = new FormControl();
  productosBuscados: Observable<Producto[]>;

  constructor(private clienteService: ClientesService, private activateRoute: ActivatedRoute, 
    private router: Router, private facturasService: FacturasService) {
    this.factura=<Factura>{};
    this.factura.listLineaFacturas = [];
   }

  ngOnInit() {
    this.activateRoute.paramMap.subscribe(params =>{
      let clienteId = +params.get('clienteId');
      console.log(clienteId);
      this.clienteService.getClienteById(clienteId).subscribe(cliente => this.factura.cliente_id = cliente)
    }),

    this.productosBuscados = this.busquedaDinamica.valueChanges
      .pipe(
        map(value => typeof value === 'string'? value: value.nombre),
        flatMap(value => value? this._filter(value) : [])
      );
  }

  goDetalleClientes(id: number){
    this.router.navigate(['/clientes/ver/' + id]);
  }

  private _filter(value: string): Observable<Producto[]> {
    const filterValue = value.toLowerCase();
    return this.facturasService.getProductosFiltrados(filterValue);
  }

  productoSeleccionado(event: MatAutocompleteSelectedEvent): void{

    let producto = event.option.value as Producto;
    console.log(producto);

    if(this.comprobarProducto(producto.id)){
      this.incrementoCantidad(producto.id);
    }else{
      let lineaFactura : LineaFactura;
      lineaFactura = new LineaFactura();
      lineaFactura.producto = producto;      
      this.factura.listLineaFacturas.push(lineaFactura);
    }

    this.busquedaDinamica.setValue(''); //dejamos limpio el autocomplete para buscar otro producto.
    event.option.focus();
    event.option.deselect();
  }

  updateCantidad(id: number, event: any): void{
    let cantidad: number = event.target.value as number;
    
    this.factura.listLineaFacturas = this.factura.listLineaFacturas.map((linea:LineaFactura) =>{
    if(id == linea.producto.id){
      linea.cantidad = cantidad;
    }
    return linea;
    });

  }

  private comprobarProducto(id: number): boolean{
    let comprobacion = false;
    this.factura.listLineaFacturas.forEach((linea: LineaFactura) =>{
      if(id === linea.producto.id){
        comprobacion = true; 
      }
    })
    return comprobacion;
  }

  private incrementoCantidad(id: number): void{    
    this.factura.listLineaFacturas = this.factura.listLineaFacturas.map((linea:LineaFactura) =>{
    if(id === linea.producto.id){
      linea.cantidad += 1;
    }
    return linea;
    });
  } 

  eliminarLinea(id: number): void{
    this.factura.listLineaFacturas = this.factura.listLineaFacturas.filter((linea: LineaFactura) => id !== linea.producto.id);
  }

  crearFactura(id: number): void{
    console.log(this.factura)
    this.facturasService.addFactura(this.factura).subscribe(factura => {
      Swal.fire('Nueva Factura', `${this.factura.titulo} añadida correctamente!`, 'success');
      this.router.navigate(['/clientes/ver/'+ id]);
    });
  }


}
