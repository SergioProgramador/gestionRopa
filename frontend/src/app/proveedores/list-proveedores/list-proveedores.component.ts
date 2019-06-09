import { Component, OnInit } from '@angular/core';
import { ProveedoresService } from '../proveedores.service';
import { Proveedores } from '../proveedores. model';
import { Observable } from 'rxjs';
import { DataSource } from '@angular/cdk/table';
import { EditProveedorComponent } from '../edit-proveedor/edit-proveedor.component';
import swal from 'sweetalert2';
import { ModalService } from 'src/app/modal.service';


@Component({
  selector: 'app-list-proveedores',
  templateUrl: './list-proveedores.component.html',
  styleUrls: ['./list-proveedores.component.scss']
})
export class ListProveedoresComponent implements OnInit {


  errorMessage: String;
  proveedores: Proveedores[];

  proveedorSeleccionado: Proveedores;

  constructor(private service: ProveedoresService, private modalService: ModalService) { }

  ngOnInit() {
    this.service.getProveedores().subscribe(
      proveedores => {
        this.proveedores = proveedores;
      })
  }

  delete(proveedor: Proveedores): void{
    swal.fire({
      title: '¿Estás seguro?',
      text: `¿Seguro que desea eliminar el proveedor ${proveedor.empresa}?`,
      type: 'warning',
      showCancelButton: true,
      confirmButtonColor: '#3085d6',
      cancelButtonColor: '#d33',
      confirmButtonText: 'Sí, eliminar!',
      cancelButtonText: "Cancelar",
      reverseButtons: true
    }).then((result) => {
      if (result.value) {
        this.service.removeProveedor(proveedor.id).subscribe(
          response => {
            this.proveedores = this.proveedores.filter(pro => pro !== proveedor)
            swal.fire(
              'Proveedor eliminado!',
              `Proveedor ${proveedor.empresa} eliminado con éxito`,
              'success'
            )
          }
        );
        swal.fire(  
          'Eliminado!',
          'El proveedor ha sido eliminado',
          'success'
        )
      }
    })
  }

  abrirModal2(proveedor: Proveedores){
    this.proveedorSeleccionado = proveedor;
    this.modalService.openModal2();
  }

  abrirModal(){
    this.modalService.openModal();
  }

}

