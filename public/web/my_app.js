class MyApp extends React.Component {

	constructor(props) {
		super(props);
		this.state = {
			plugSelected: null,
			groupSelected: null,// .name .members
			selectedPlugOfGroup: null,
			//plugView:true,
			//colorView:["btn btn-block btn-info", "btn btn-block btn-outline-secondary"]
		};
    }
    updatePlugSelected(plug) {
		this.setState({ plugSelected: plug });
	}

	updateGroupSelected(group) {
		this.setState({ groupSelected: group });
	}

	updateSelectedPlugOfGroup(plug) {
		this.setState({selectedPlugOfGroup: plug})
    }
    // changeView(){
	// 	this.setState({plugView: this.state.plugView ? false : true})
	// 	this.setState({colorView: this.state.plugView ? ["btn btn-block btn-outline-secondary", "btn btn-block btn-info"] : ["btn btn-block btn-info", "btn btn-block btn-outline-secondary"]})
	// }

	render() {
		return (
			<div className="container">
				<div className="row">
                <h3>Welcome to IoT Hub from ECE448/ECE528@IIT!</h3>
					<hr className="col-sm-12" />
				</div>
				<br></br>
				
				
				<br></br>

				
                        
            
					 <div className="col-sm-12"> 
						<Groups
						updateGroupSelected={group => this.updateGroupSelected(group)}
						groupSelected={this.state.groupSelected}
                        updatePlugSelected={plug => this.updatePlugSelected(plug)}
                        plugSelected={this.state.plugSelected}
						updateSelectedPlugOfGroup={plug => this.updateSelectedPlugOfGroup(plug)}
						selectedPlugOfGroup={this.state.selectedPlugOfGroup}
						 />
					</div>
				</div>
        );
	}
}

window.MyApp = MyApp;
                    
               