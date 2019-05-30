import { Component, OnInit } from '@angular/core';
import { Factura } from '../factura';
import { ClientesService } from 'src/app/clientes/clientes.service';
import { ActivatedRoute, Router } from '@angular/router';
import {map, startWith, flatMap} from 'rxjs/operators';
import { FormControl } from '@angular/forms';
import { Observable } from 'rxjs';
import { FacturasService } from '../facturas.service';
import { Producto } from 'src/app/productos/producto';

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

}
