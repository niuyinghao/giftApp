//
// Project home: https://github.com/jaxio/celerio-angular-quickstart
//
// Source code generated by Celerio, an Open Source code generator by Jaxio.
// Documentation: http://www.jaxio.com/documentation/celerio/
// Source code: https://github.com/jaxio/celerio/
// Follow us on twitter: @jaxiosoft
// This header can be customized in Celerio conf...
// Template pack-angular:web/src/app/entities/entity-detail.component.ts.e.vm
//
import {Component, EventEmitter, Input, OnDestroy, OnInit, Output} from '@angular/core';
import {ActivatedRoute, Router} from '@angular/router';
import {MessageService} from '../../service/message.service';
import {Artifact} from './artifact';
import {ArtifactService} from './artifact.service';
import {Config_} from '../config_/config_';
import {User_} from '../user_/user_';
import {App_} from '../app_/app_';

@Component({
    moduleId: module.id,
	templateUrl: 'artifact-detail.component.html',
	selector: 'artifact-detail',
})
export class ArtifactDetailComponent implements OnInit, OnDestroy {
    artifact : Artifact;

    private params_subscription: any;


    @Input() sub : boolean = false;
    @Input() // used to pass the parent when creating a new Artifact
    set config(config : Config_) {
        this.artifact = new Artifact();
        this.artifact.config = config;
    }

    @Input() // used to pass the parent when creating a new Artifact
    set user(user : User_) {
        this.artifact = new Artifact();
        this.artifact.user = user;
    }

    @Input() // used to pass the parent when creating a new Artifact
    set app(app : App_) {
        this.artifact = new Artifact();
        this.artifact.app = app;
    }

    @Output() onSaveClicked = new EventEmitter<Artifact>();
    @Output() onCancelClicked = new EventEmitter();

    constructor(private route: ActivatedRoute, private router: Router, private messageService: MessageService, private artifactService: ArtifactService) {
    }

    ngOnInit() {
        if (this.sub) {
            return;
        }

        this.params_subscription = this.route.params.subscribe(params => {
            let id = params['id'];
            console.log('ngOnInit for artifact-detail ' + id);

            if (id === 'new') {
                this.artifact = new Artifact();
            } else {
                this.artifactService.getArtifact(id)
                    .subscribe(artifact => {
                            this.artifact = artifact;
                        },
                        error =>  this.messageService.error('ngOnInit error', error)
                    );
            }
        });
    }

    ngOnDestroy() {
        if (!this.sub) {
            this.params_subscription.unsubscribe();
        }
    }

    gotoConfig() {
        this.router.navigate(['/config_', this.artifact.config.id]);
    }

    clearConfig() {
        this.artifact.config = null;
    }

    gotoUser() {
        this.router.navigate(['/user_', this.artifact.user.id]);
    }

    clearUser() {
        this.artifact.user = null;
    }

    gotoApp() {
        this.router.navigate(['/app_', this.artifact.app.id]);
    }

    clearApp() {
        this.artifact.app = null;
    }

    onSave() {
        this.artifactService.update(this.artifact).
            subscribe(
                artifact => {
                    this.artifact = artifact;
                    if (this.sub) {
                        this.onSaveClicked.emit(this.artifact);
                        this.messageService.info('Saved OK and msg emitted', 'Angular Rocks!')
                    } else {
                        this.messageService.info('Saved OK', 'Angular Rocks!')
                    }
                },
                error =>  this.messageService.error('Could not save', error)
            );
    }

    onCancel() {
        if (this.sub) {
            this.onCancelClicked.emit("cancel");
            this.messageService.info('Cancel clicked and msg emitted', 'Angular Rocks!')
        }
    }

}
