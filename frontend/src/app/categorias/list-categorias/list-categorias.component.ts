import { Component, OnInit, Inject } from '@angular/core';
import { Router } from '@angular/router';
import { CategoriasService } from '../categorias.service';
import { Categoria } from '../categoria';
import { Observable } from 'rxjs';
import { DataSource } from '@angular/cdk/table';
import { MatDialog, MatDialogRef, MAT_DIALOG_DATA, MatDialogConfig} from '@angular/material';
import { analyzeAndValidateNgModules } from '@angular/compiler';
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

  addCategoria(){
    const dialogConfig = new MatDialogConfig();
    dialogConfig.disableClose=true;
    dialogConfig.autoFocus=true;
    dialogConfig.width="60%";
    this.dialog.open(AddCategoriaComponent,
      data:{
        nombre:any;
      });
    } 
  }



@Component({
  selector: 'add-categoria-component',
  templateUrl: './add-categoria.component.html',
})
export class AddCategoriaComponent {

  constructor(
    public dialogRef: MatDialogRef<AddCategoriaComponent>,
    @Inject(MAT_DIALOG_DATA) public data: Categoria) {}

  onNoClick(): void {
    this.dialogRef.close();
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
