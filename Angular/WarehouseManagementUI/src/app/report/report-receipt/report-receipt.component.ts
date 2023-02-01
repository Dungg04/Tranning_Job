import { Component, OnInit } from '@angular/core';
import { ReportService } from '../services/report.service';

@Component({
  selector: 'app-report-receipt',
  templateUrl: './report-receipt.component.html',
  styleUrls: ['./report-receipt.component.css']
})
export class ReportReceiptComponent implements OnInit {
  receiptID: number = 1;
  p: number = 1;
  receipts: any[] = [];

  constructor(private service: ReportService) {
    for(let i=0; i<this.receipts.length; i++) {
      this.receipts.push(`item${i}`);
    }
  }

  ngOnInit(): void {
    this.service.getReportReceipt().subscribe(res=> {
      this.receipts = res;
    })
  }



}
