import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import swal from 'sweetalert2';
import { Categoria } from '../categoria';
import { CategoriasService } from '../categorias.service';
import { ModalService } from 'src/app/modal.service';

@Component({
  selector: 'app-add-categoria',
  templateUrl: './add-categoria.component.html',
  styleUrls: ['./add-categoria.component.scss']
})
export class AddCategoriaComponent implements OnInit {

  public categoria: Categoria;

  constructor(private router: Router, private service: CategoriasService, private modalService: ModalService) { 
      this.categoria = <Categoria>{};
    }

  ngOnInit() {
  }

  public create(): void {
    console.log("Ha enviado el formulario.")
    console.log(this.categoria)
    this.service.addCategoria(this.categoria).subscribe(
      response => {
        this.modalService.notificarUpload.emit(this.categoria),
        this.modalService.closeModal(),
        swal.fire('Nueva Categoría', `Categoría ${this.categoria.nombre} añadida correctamente!`, 'success')
      }      
    );
  }

  

}
