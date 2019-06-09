import { Component, OnInit } from '@angular/core';
import { Cliente } from '../cliente';
import { Router } from '@angular/router';
import { ClientesService } from '../clientes.service';
import { ModalService } from 'src/app/modal.service';
import swal from 'sweetalert2';

@Component({
  selector: 'app-add-cliente',
  templateUrl: './add-cliente.component.html',
  styleUrls: ['./add-cliente.component.scss']
})
export class AddClienteComponent implements OnInit {

  public cliente: Cliente;

  constructor(private router: Router, private service: ClientesService, private modalService: ModalService) { 
    this.cliente=<Cliente>{};
  }

  ngOnInit() {
  }

  public create(): void {
    console.log("Ha enviado el formulario.")
    console.log(this.cliente)
    this.service.addCliente(this.cliente).subscribe(
      response => {
        //this.modalService.notificarUpload.emit(this.categoria),
        this.modalService.closeModal(),
        swal.fire('Nuevo Cliente', `Cliente ${this.cliente.nombre} añadido correctamente!`, 'success')
      }      
    );
  }

  cerrarModal(){
    this.modalService.closeModal();
  }

}
