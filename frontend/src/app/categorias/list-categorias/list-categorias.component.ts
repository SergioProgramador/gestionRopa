import { Component, OnInit, Inject } from '@angular/core';
import { Router } from '@angular/router';
import { CategoriasService } from '../categorias.service';
import { Categoria } from '../categoria';
import { Observable } from 'rxjs';
import { DataSource } from '@angular/cdk/table';
import { MatDialog, MatDialogRef, MAT_DIALOG_DATA, MatDialogConfig} from '@angular/material';
import { AddCategoriaComponent } from '../add-categoria/add-categoria.component';
//import {MatPaginator, MatTableDataSource} from '@angular/material';

@Component({
  selector: 'app-list-categorias',
  templateUrl: './list-categorias.component.html',
  styleUrls: ['./list-categorias.component.scss']
})
export class ListCategoriasComponent implements OnInit {

  errorMessage: string;
  categorias: Categoria[];

  displayedColumns: string[] = ['id', 'nombre', 'icono'];
  dataSource = new categoriasDataSource(this.service);

  //@ViewChild(MatPaginator) paginator: MatPaginator;

  nombre:string;

  constructor(private router: Router, private service: CategoriasService, public dialog: MatDialog) { }

  ngOnInit() {

  }

  addCategoria(): void{
    let dialogRef = this.dialog.open(AddCategoriaComponent, {
      
    });

    dialogRef.afterClosed().subscribe(result => {
      console.log('Pop-up se ha cerrado.');
      console.log(result);
    });
  }

}





export class categoriasDataSource extends DataSource<any>{

  constructor(private service: CategoriasService){
    super();
  }
  
  connect():Observable<Categoria[]>{
    return this.service.getCategorias();
  }

  disconnect(){

  }
  
}
