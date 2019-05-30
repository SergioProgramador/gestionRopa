import { Component, OnInit } from '@angular/core';
import { Cliente } from '../cliente';
import { Router, ActivatedRoute } from '@angular/router';
import { ClientesService } from '../clientes.service';
import { Factura } from 'src/app/facturas/factura';
import { FacturasService } from 'src/app/facturas/facturas.service';
import swal from 'sweetalert2';

@Component({
  selector: 'app-ver-cliente',
  templateUrl: './ver-cliente.component.html',
  styleUrls: ['./ver-cliente.component.scss']
})
export class VerClienteComponent implements OnInit {

  errorMessage: string;
  cliente:  Cliente;

  constructor(private router: Router, private route: ActivatedRoute, private service: ClientesService,
    private facturasService: FacturasService) { 
  
  }

  ngOnInit() {
    this.route.paramMap.subscribe(
      param => {
        let clienteId = +param.get('id');
        console.log(clienteId);
        this.service.getClienteById(clienteId).subscribe(
          cliente => this.cliente = cliente,
          error => this.errorMessage = <any> error
          );
      }
    );
    
  }

  goDetalleFactura(id: number){
    this.router.navigate(['/facturas/ver/' + id]);
  }

  goAddFactura(id: number){
    this.router.navigate(['/facturas/create/' + id]);
  }

  delete(factura: Factura): void{
    swal.fire({
      title: '¿Estás seguro?',
      text: `¿Seguro que desea eliminar la factura Nº: ${factura.id}?`,
      type: 'warning',
      showCancelButton: true,
      confirmButtonColor: '#3085d6',
      cancelButtonColor: '#d33',
      confirmButtonText: 'Sí, eliminar!',
      cancelButtonText: "Cancelar",
      reverseButtons: true
    }).then((result) => {
      if (result.value) {
        this.facturasService.removeFactura(factura.id).subscribe(
          response => {
            this.cliente.facturas = this.cliente.facturas.filter(fac => fac !== factura)
            swal.fire(
              'Factura eliminada!',
              `Factura Nº:${factura.id} eliminada con éxito`,
              'success'
            )
          }
        );
        swal.fire(  
          'Eliminado!',
          'El cliente ha sido eliminado',
          'success'
        )
      }
    })
  }


}
