import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import swal from 'sweetalert2';
import { Categoria } from '../categoria';
import { CategoriasService } from '../categorias.service';
import { ModalService } from 'src/app/modal.service';
import { ListCategoriasComponent } from '../list-categorias/list-categorias.component';

@Component({
  selector: 'app-add-categoria',
  templateUrl: './add-categoria.component.html',
  styleUrls: ['./add-categoria.component.scss']
})
export class AddCategoriaComponent implements OnInit {

  public categoria: Categoria;

  constructor(private router: Router, private service: CategoriasService, private modalService: ModalService, private listCategorias: ListCategoriasComponent) { 
      this.categoria = <Categoria>{};
    }

  ngOnInit() {
  }

  public create(): void {
    console.log("Ha enviado el formulario.")
    console.log(this.categoria)
    this.service.addCategoria(this.categoria).subscribe(
      response => {
        this.modalService.closeModal(),
        this.modalService.notificarUpload.emit(this.categoria);
       //this.listCategorias.categorias = this.listCategorias.categorias.filter(cat => cat === this.categoria);
        swal.fire('Nueva Categoría', `Categoría ${this.categoria.nombre} añadida correctamente!`, 'success'),
        this.router.navigate(['/categorias/showcategorias']);
        
      }      
    );
  }

  cerrarModal(){
    this.modalService.closeModal();
    this.categoria.nombre=null;
  }

  

}
