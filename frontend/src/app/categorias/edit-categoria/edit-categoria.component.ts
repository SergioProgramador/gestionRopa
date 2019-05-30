import { Component, OnInit, Inject, Input } from '@angular/core';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material';
import { CategoriasService } from '../categorias.service';
import { Router } from '@angular/router';
import { Categoria } from '../categoria';
import swal from 'sweetalert2';
import { ModalService } from 'src/app/modal.service';

@Component({
  selector: 'app-edit-categoria',
  templateUrl: './edit-categoria.component.html',
  styleUrls: ['./edit-categoria.component.scss']
})
export class EditCategoriaComponent implements OnInit {

  @Input() categoria: Categoria;
  errorMessage: string;


  constructor( private service: CategoriasService, private router: Router, public modalService: ModalService) {

     }

  ngOnInit() {
    console.log(this.categoria);
  }

  update() {
    this.service.updateCategoria(this.categoria.id, this.categoria).subscribe(
      update_categoria => {
        swal.fire('Categoría Editada', `Categoría ${update_categoria.categoria.nombre} se ha editado correctamente!`, 'success');
      },
      error => this.errorMessage = <any>error
    );
  }

}
