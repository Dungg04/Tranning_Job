import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit{
  // public name = 'Dungg';
  // public age = 15;
  // public traiCay = ['Táo', 'Nho', 'Cam'];
  // public traiCay2 = [{ten: 'Táo', gia: 12, haGia: true}, {ten: 'Nho', gia: 20, haGia: false}, {ten: 'Cam', gia: -15, haGia: true}];

  public cities = [{name: 'Chọn thành phố', districts:[]},{name: 'An Giang', districts:['An Giang 1', 'An Giang 2', 'An Giang 3', 'An Giang 4']}, {name: 'Hà Nội', districts:['Hà Nội 1', 'Hà Nội 2', 'Hà Nội 3', 'Hà Nội 4']}];

  public districtsList: string[] = ['Chọn quận/huyện'];


  ngOnInit(): void {
  }

  // public resetName() : void {
  //   this.age = 0;
  // }

  public changeCity(event: any) : void {
    const city = event.target.value;
    this.districtsList = this.cities.find(data => data.name === city)?.districts || [];
  }
}
