import { LineaFactura } from '../lineas-facturas/linea-factura';
import { Cliente } from '../clientes/cliente';

export interface Factura {
    id:number;
    titulo:string;
    descripcion:string;
    iva:number;
    fecha_venciminento:any;
    create_time: any;
    update_time: any;
    listLineaFacturas: Array<LineaFactura>[];
    cliente_id:Cliente;
    total:number;
    subTotal:number;
}
