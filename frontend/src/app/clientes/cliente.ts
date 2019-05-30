import { Factura } from '../facturas/factura';

export interface Cliente {
    id:number;
    nombre: string;
    apellidos: string;
    direccion: string;
    codigo_postal: string;
    ciudad: string;
    email: string;
    movil: string;
    sexo: string;
    create_time: any;
    update_time: any;
    facturas: Factura[];
}
