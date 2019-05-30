import { Producto } from '../productos/producto';

export class LineaFactura {
    id:number;
    cantidad: number = 1;
    producto:Producto;
    importe: number;

    public getImporte(): number{
        return this.cantidad * this.producto.precio;
    }
}

