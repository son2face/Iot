import {async, ComponentFixture, TestBed} from '@angular/core/testing';
import {NodeValueComponent} from "./NodeValue.Component";

describe('NodeValueComponent', () => {
    let component: NodeValueComponent;
    let fixture: ComponentFixture<NodeValueComponent>;
    beforeEach(async(() => {
        TestBed.configureTestingModule({
            declarations: [NodeValueComponent]
        })
            .compileComponents();
    }));
    beforeEach(() => {
        fixture = TestBed.createComponent(NodeValueComponent);
        component = fixture.componentInstance;
        fixture.detectChanges();
    });
    it('should be created', () => {
        expect(component).toBeTruthy();
    });
});