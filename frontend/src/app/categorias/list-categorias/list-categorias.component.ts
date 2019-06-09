import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { CategoriasService } from '../categorias.service';
import { Categoria } from '../categoria';
import swal from 'sweetalert2';
import { ModalService } from 'src/app/modal.service';


@Component({
  selector: 'app-list-categorias',
  templateUrl: './list-categorias.component.html',
  styleUrls: ['./list-categorias.component.scss']
})
export class ListCategoriasComponent implements OnInit {

  errorMessage: string;
  public categorias: Categoria[];

  categoriaSeleccionada:Categoria;

  nombre:string;

  constructor(private router: Router, private service: CategoriasService, private modalService: ModalService) { }

  ngOnInit() {
    this.service.getCategorias().subscribe(
      categorias => {
        this.categorias = categorias;
        
      });

      this.modalService.notificarUpload.subscribe(cat =>{
        this.categorias.map(catOriginal =>{
          if(cat.id == catOriginal.id){
            this.categorias.unshift(cat);
          }
          return this.categorias;
        })
      })

  }

  delete(categoria: Categoria): void{
    swal.fire({
      title: '¿Estás seguro?',
      text: `¿Seguro que desea eliminar la categoría ${categoria.nombre}?`,
      type: 'warning',
      showCancelButton: true,
      confirmButtonColor: '#3085d6',
      cancelButtonColor: '#d33',
      confirmButtonText: 'Sí, eliminar!',
      cancelButtonText: "Cancelar",
      reverseButtons: true
    }).then((result) => {
      if (result.value) {
        this.service.removeCategoria(categoria.id).subscribe(
          response => {
            this.categorias = this.categorias.filter(cat => cat !== categoria)
            swal.fire(
              'Categoria eliminada!',
              `Categoria ${categoria.nombre} eliminada con éxito`,
              'success'
            )
          }
        );
        swal.fire(  
          'Eliminada!',
          'La categoría ha sido eliminada',
          'success'
        )
      }
    })
  }

  openModal2(categoria: Categoria){
    this.categoriaSeleccionada = categoria;
    this.modalService.openModal2();
  }

  openModal(){
    this.modalService.openModal();
  }

}
  

