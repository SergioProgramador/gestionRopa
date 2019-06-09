import { Proveedores } from '../proveedores/proveedores. model';
import { Categoria } from '../categorias/categoria';

export class Producto {
    id:number;
    nombre: string;
    descripcion: string;
    precio: number;
    talla: string;
    color: string;
    imagen: string;
    material: string;
    stock: number;
    create_time: any;
    update_time: any;
    proveedores: Proveedores;
    categorias: Array<Categoria>[];
}
