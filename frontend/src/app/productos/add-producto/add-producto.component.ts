import { Component, OnInit } from '@angular/core';
import { Producto } from '../producto';
import { ProductosService } from '../productos.service';
import { ProveedoresService } from 'src/app/proveedores/proveedores.service';
import { Proveedores } from 'src/app/proveedores/proveedores. model';
import { CategoriasService } from 'src/app/categorias/categorias.service';
import { Categoria } from 'src/app/categorias/categoria';
import { Router } from '@angular/router';

@Component({
  selector: 'app-add-producto',
  templateUrl: './add-producto.component.html',
  styleUrls: ['./add-producto.component.scss']
})
export class AddProductoComponent implements OnInit {

  producto: Producto;
  proveedores: Proveedores[];
  categorias: Categoria[];

  constructor(private productoService: ProductosService, private proveedoresService: ProveedoresService, private categoriasService: CategoriasService, private router: Router) {
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
      });

    console.log(this.categorias);
  }

  gotoProductos(){
    this.router.navigate(['/productos/showproductos']);
  }

}
