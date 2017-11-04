import { async, ComponentFixture, TestBed } from '@angular/core/testing';
import { NodeComponent } from "./Node.Component";
describe('ReportComponent', () => {
let component: NodeComponent;
let fixture: ComponentFixture<NodeComponent>;
beforeEach(async(() => {
TestBed.configureTestingModule({
declarations: [ NodeComponent ]
})
.compileComponents();
}));
beforeEach(() => {
fixture = TestBed.createComponent(NodeComponent);
component = fixture.componentInstance;
fixture.detectChanges();
});
it('should be created', () => {
expect(component).toBeTruthy();
});
});