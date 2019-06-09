import { Component, OnInit } from '@angular/core';
import { Producto } from '../producto';
import { ProductosService } from '../productos.service';
import { ProveedoresService } from 'src/app/proveedores/proveedores.service';
import { Proveedores } from 'src/app/proveedores/proveedores. model';
import { CategoriasService } from 'src/app/categorias/categorias.service';
import { Categoria } from 'src/app/categorias/categoria';
import { Router } from '@angular/router';
import swal from 'sweetalert2';
import { AuthServiceService } from 'src/app/autentificacion/auth-service.service';

@Component({
  selector: 'app-add-producto',
  templateUrl: './add-producto.component.html',
  styleUrls: ['./add-producto.component.scss']
})
export class AddProductoComponent implements OnInit {

  errorMessage: string;
  public producto: Producto;
  proveedores: Proveedores[];
  categorias: Categoria[];

  constructor(private productoService: ProductosService, private proveedoresService: ProveedoresService, private categoriasService: CategoriasService, 
    private router: Router, private authService: AuthServiceService) {
    this.producto = <Producto>{};
   }

  ngOnInit() {
    this.proveedoresService.getProveedores().subscribe(
      proveedores => {
        this.proveedores = proveedores;
      });
    
    this.categoriasService.getCategorias().subscribe(
      categorias => {
        this.categorias = categorias;
        console.log(this.categorias);
      }); 
  }

  onSubmit() {
    this.productoService.addProducto(this.producto).subscribe(
      new_producto => {
        console.log(new_producto);
        this.router.navigate(['/productos/showproductos']); 
        swal.fire('Nuevo Producto', `Producto ${new_producto.producto.nombre} añadido correctamente!`, 'success');
      },
      error => this.errorMessage = <any>error
    );
  }

  gotoProductos(){
    this.router.navigate(['/productos/showproductos']);
  }

}
