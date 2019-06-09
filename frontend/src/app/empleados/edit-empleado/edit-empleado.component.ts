import { Component, OnInit, Input } from '@angular/core';
import { EmpleadoService } from '../empleado.service';
import { Router } from '@angular/router';
import { ModalService } from 'src/app/modal.service';
import { Empleado } from '../empleado';

@Component({
  selector: 'app-edit-empleado',
  templateUrl: './edit-empleado.component.html',
  styleUrls: ['./edit-empleado.component.scss']
})
export class EditEmpleadoComponent implements OnInit {

  @Input() empleado: Empleado;
  errorMessage: string;

  constructor(private service: EmpleadoService, private router: Router, private modalService: ModalService) { }

  ngOnInit() {
  }

  cerrarModal(){
    this.modalService.closeModal2();
  }

}
