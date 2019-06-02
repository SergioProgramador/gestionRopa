import { Component, OnInit, Input } from '@angular/core';
import { Cliente } from '../cliente';
import { ClientesService } from '../clientes.service';
import { Router } from '@angular/router';
import swal from 'sweetalert2';

@Component({
  selector: 'app-edit-cliente',
  templateUrl: './edit-cliente.component.html',
  styleUrls: ['./edit-cliente.component.scss']
})
export class EditClienteComponent implements OnInit {

  @Input() cliente: Cliente;
  errorMessage: string;

  constructor(private service: ClientesService, private router: Router) { }

  ngOnInit() {
    console.log(this.cliente);
  }

  update() {
    this.cliente.facturas=null;
    this.service.updateCliente(this.cliente.id, this.cliente).subscribe(
      update_cliente => {
        swal.fire('Cliente editado', `Cliente ${update_cliente.clinete.nombre} se ha editado correctamente!`, 'success');
      },
      error => this.errorMessage = <any>error
    );
  }

}
