import { Component, OnInit } from '@angular/core';
import { Producto } from '../producto';
import { ProductosService } from '../productos.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-list-productos',
  templateUrl: './list-productos.component.html',
  styleUrls: ['./list-productos.component.scss']
})
export class ListProductosComponent implements OnInit {

  productos: Producto[];

  constructor(private productosService: ProductosService, private router: Router) { }

  ngOnInit() {
    this.productosService.getProductos().subscribe(
      productos => {
        this.productos = productos;
      });

      this.productosService.verificarSubida.subscribe(producto => {
        this.productos = this.productos.map(productoAnterior => {
          if(producto.id == productoAnterior.id){
            productoAnterior.imagen = producto.imagen;
          }
          return productoAnterior;
        })
      })
  }

  goToVerProducto(id: number): void{
    this.router.navigate(['/productos/ver/'+id]);
  }

}
