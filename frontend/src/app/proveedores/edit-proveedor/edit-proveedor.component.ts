import { Component, OnInit, Inject, Input } from '@angular/core';
import { Proveedores } from '../proveedores. model';
import { Router } from '@angular/router';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material';
import { ProveedoresService } from '../proveedores.service';
import swal from 'sweetalert2';
import { ModalService } from 'src/app/modal.service';

@Component({
  selector: 'app-edit-proveedor',
  templateUrl: './edit-proveedor.component.html',
  styleUrls: ['./edit-proveedor.component.scss']
})
export class EditProveedorComponent implements OnInit {

  @Input() proveedor: Proveedores;
  errorMessage: string;

  constructor(private service: ProveedoresService, private router: Router, private modalService: ModalService) {

    }

  ngOnInit() {
  }

  update() {
    this.service.updateProveedor(this.proveedor.id, this.proveedor).subscribe(
      update_proveedor => {
        this.modalService.closeModal2();
        swal.fire('Proveedor editado', `Proveedor ${update_proveedor.proveedor.empresa} se ha editado correctamente!`, 'success');
      },
      error => this.errorMessage = <any>error
    );
  }

  cerrarModal(){
    this.modalService.closeModal2();
  }

}
