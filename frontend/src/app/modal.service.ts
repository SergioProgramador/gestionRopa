import { Injectable, EventEmitter} from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class ModalService {

  private _notificarUpload = new EventEmitter<any>();
  modal: boolean = false;
  modal2 : boolean = false;

  constructor() { }

  get notificarUpload(): EventEmitter<any>{
    return this._notificarUpload;
  }

  openModal(){
    this.modal = true;
  }

  closeModal(){
    this.modal = false;
  }

  openModal2(){
    this.modal2 = true;
  }
  closeModal2(){
    this.modal2 = false;
  }

}
