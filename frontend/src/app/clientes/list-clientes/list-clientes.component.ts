import { Component, OnInit } from '@angular/core';
import { ClientesService } from '../clientes.service';
import { Cliente } from '../cliente';
import swal from 'sweetalert2';
import { ModalService } from 'src/app/modal.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-list-clientes',
  templateUrl: './list-clientes.component.html',
  styleUrls: ['./list-clientes.component.scss']
})
export class ListClientesComponent implements OnInit {

  errorMessage: string;
  clientes: Cliente[];

  clienteSeleccionado: Cliente;

  constructor(private service: ClientesService, private modalService: ModalService, private router: Router) { }

  ngOnInit() {
    this.service.getClientes().subscribe(
      clientes => {
        this.clientes = clientes;
      });
  }

  delete(cliente: Cliente): void{
    swal.fire({
      title: '¿Estás seguro?',
      text: `¿Seguro que desea eliminar el cliente ${cliente.nombre}?`,
      type: 'warning',
      showCancelButton: true,
      confirmButtonColor: '#3085d6',
      cancelButtonColor: '#d33',
      confirmButtonText: 'Sí, eliminar!',
      cancelButtonText: "Cancelar",
      reverseButtons: true
    }).then((result) => {
      if (result.value) {
        this.service.removeCliente(cliente.id).subscribe(
          response => {
            this.clientes = this.clientes.filter(cat => cat !== cliente)
            swal.fire(
              'Cliente eliminado!',
              `Cliente ${cliente.nombre} eliminado con éxito`,
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

  abrirModal2(cliente: Cliente){
    this.clienteSeleccionado = cliente;
    this.modalService.openModal2();
  }

  abrirModal(){
    this.modalService.openModal();
  }

  verDetalle(id: number){
    this.router.navigate(["/clientes/ver/"+id]);
  }


}
