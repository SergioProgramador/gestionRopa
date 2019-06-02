import { Component, OnInit } from '@angular/core';
import { Producto } from '../producto';
import { ProductosService } from '../productos.service';
import { ActivatedRoute, Router } from '@angular/router';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-ver-producto',
  templateUrl: './ver-producto.component.html',
  styleUrls: ['./ver-producto.component.scss']
})
export class VerProductoComponent implements OnInit {

  producto: Producto;
  private imgSeleccionada: File;

  constructor(private productoService: ProductosService, private activatedRouter: ActivatedRoute, private router: Router) { }

  ngOnInit() {
    this.activatedRouter.paramMap.subscribe(params =>{
      let id = +params.get('id'); //con el signo +, convertimos el id en un number.
      if(id){
        this.productoService.getProductoById(id).subscribe( producto =>{
          this.producto = producto;
        });
      }
    });
  }

  seleccionarImg(event){
    this.imgSeleccionada = event.target.files[0];
    console.log(this.imgSeleccionada);
    if(this.imgSeleccionada.type.indexOf('image') < 0){
      Swal.fire('Error al seleccionar la imagen: ', 'El archivo debe ser del tipo imagen.', 'error');
    }
  }

  uploadImagen(){
    if(!this.imgSeleccionada){
      Swal.fire('Error al subir: ', 'Selecciona una imagen', 'error');
    }else{
      this.productoService.uploadImagen(this.imgSeleccionada, this.producto.id).subscribe(
        producto => {
          this.producto = producto;
          this.productoService.verificarSubida.emit(this.producto);
          this.router.navigate(['/productos/showproductos']);
          Swal.fire('La imagen se ha subido correctamente!', `La foto se ha subido con éxito: ${this.producto.imagen}`, 'success');          
        }
      );
    } 
  }

  gotoProductos(){
    this.router.navigate(['/productos/showproductos']);
  }

}
