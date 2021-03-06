import { Component, OnInit } from '@angular/core';
import { Producto } from '../producto';
import { ProductosService } from '../productos.service';
import { ActivatedRoute, Router } from '@angular/router';
import Swal from 'sweetalert2';
import { AuthServiceService } from 'src/app/autentificacion/auth-service.service';

@Component({
  selector: 'app-ver-producto',
  templateUrl: './ver-producto.component.html',
  styleUrls: ['./ver-producto.component.scss']
})
export class VerProductoComponent implements OnInit {

  producto: Producto;
  private imgSeleccionada: File;

  constructor(private productoService: ProductosService, private activatedRouter: ActivatedRoute, private router: Router, private authService: AuthServiceService) { }

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
          Swal.fire('La imagen se ha subido correctamente!', `La foto se ha subido con éxito`, 'success');          
        }
      );
    } 
  }

  gotoProductos(){
    this.router.navigate(['/productos/showproductos']);
  }

  delete(producto: Producto): void{
    Swal.fire({
      title: '¿Estás seguro?',
      text: `¿Seguro que desea eliminar este producto?`,
      type: 'warning',
      showCancelButton: true,
      confirmButtonColor: '#3085d6',
      cancelButtonColor: '#d33',
      confirmButtonText: 'Sí, eliminar!',
      cancelButtonText: "Cancelar",
      reverseButtons: true
    }).then((result) => {
      if (result.value) {
        this.productoService.removeProducto(producto.id).subscribe(
          response => {   
            Swal.fire(
              'Producto eliminado!',
              `Producto ${producto.nombre} eliminado con éxito`,
              'success'
            );
            this.router.navigate(['/productos/showproductos']);
          }
        );
        Swal.fire(  
          'Eliminado!',
          'El producto ha sido eliminado',
          'success'
        )
      }
    })
  }

}
