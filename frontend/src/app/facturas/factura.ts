import { LineaFactura } from '../lineas-facturas/lineaFactura';
import { Cliente } from '../clientes/cliente';

export class Factura {
    id:number;
    titulo:string;
    descripcion:string;
    iva:number = 21;
    fecha_venciminento:any;
    create_time: any;
    update_time: any;
    listLineaFacturas: LineaFactura[];
    cliente_id:Cliente;
    total:number;
    subTotal:number;

    getSubTotal(): number{
        this.subTotal = 0;
        this.listLineaFacturas.forEach((linea: LineaFactura) => {
            this.subTotal = this.subTotal + linea.getImporte();       
        }); 
        return this.subTotal;
    }
    
    getTotal(): number{
        this.total=0;
        this.total = this.getSubTotal()*this.iva;
        return this.total;
    }
      
}
