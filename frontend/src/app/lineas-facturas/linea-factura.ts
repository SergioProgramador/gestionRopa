import { Producto } from '../productos/producto';

export interface LineaFactura {
    id:number;
    cantidad: number;
    producto:Producto;
    importe: number;
}
