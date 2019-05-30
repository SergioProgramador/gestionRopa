import { Component, OnInit, Inject } from '@angular/core';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material';
import { Router } from '@angular/router';
import { Proveedores } from '../proveedores. model';
import { ProveedoresService } from '../proveedores.service';
import swal from 'sweetalert2';

@Component({
  selector: 'app-add-proveedor',
  templateUrl: './add-proveedor.component.html',
  styleUrls: ['./add-proveedor.component.scss']
})
export class AddProveedorComponent implements OnInit {

  proveedor: Proveedores;
  errorMessage: string;

  constructor(private service: ProveedoresService, private router: Router) {
      this.proveedor = <Proveedores>{};
     }

  ngOnInit() {
  }

  onSubmit() {
    this.service.addProveedor(this.proveedor).subscribe(
      new_proveedor => {
        console.log(new_proveedor);
        swal.fire('Nuevo Proveedor', `Proveedor ${new_proveedor.proveedor.empresa} añadido correctamente!`, 'success');
      },
      error => this.errorMessage = <any>error
    );
  }

}
