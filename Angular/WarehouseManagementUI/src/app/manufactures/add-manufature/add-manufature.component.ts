import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { ManufactureService } from '../services/manufacture.service';

@Component({
  selector: 'app-add-manufature',
  templateUrl: './add-manufature.component.html',
  styleUrls: ['./add-manufature.component.css']
})
export class AddManufatureComponent implements OnInit{
  public manufature: any={
    manufactureID: '',
    manufactureName: '',
    address: '',
    phoneNumber: ''
  };

  constructor(private service: ManufactureService, private router: Router) {}

  ngOnInit(): void {

  }

  addMenufacture() {
    this.service.addMenufacture(this.manufature.manufactureID, this.manufature).subscribe(data => {
      alert("Thêm mới thành công")
      this.router.navigateByUrl('/addReceipt');
    }, error => {
      alert("Thông tin nhà cung cấp chưa đáp ứng")
    }
    )
  }


}
