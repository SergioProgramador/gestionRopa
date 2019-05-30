import { Component, OnInit } from '@angular/core';
import { FacturasService } from '../facturas.service';
import { Factura } from '../factura';
import { ActivatedRoute, Router } from '@angular/router';

@Component({
  selector: 'app-ver-factura',
  templateUrl: './ver-factura.component.html',
  styleUrls: ['./ver-factura.component.scss']
})
export class VerFacturaComponent implements OnInit {

  factura: Factura;

  constructor(private service: FacturasService, private activateRoute: ActivatedRoute, private router: Router) { 

  }

  ngOnInit() {
    this.activateRoute.paramMap.subscribe(params =>{
      let id = +params.get('id');
      console.log(id);
      this.service.getFacturaById(id).subscribe(factura =>{
        this.factura = factura
      }        
      );
    });
  }

  goClientes(id: number){
    this.router.navigate(['/clientes/ver/'+id]);
  }

}
