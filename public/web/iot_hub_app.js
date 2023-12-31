/**
 * The App class is a controller holding the global state.
 * It creates all children controllers in render().
 */


class IoTHubApp extends React.Component {

	constructor(props) {
		super(props);
		this.state = {
			plugSelected: null
		};
	}

	updatePlugSelected(plug) {
		this.setState({ plugSelected: plug });
	}

	render() {
		return (
			<div className="container">
				<div className="row">
					<h3>Welcome to IoT Hub from ECE448/ECE528@IIT!</h3>
					<hr className="col-sm-12" />
				</div>
					<div className="row">
							<div className="col-sm-2">
									<Plugs
										updatePlugSelected={plug => this.updatePlugSelected(plug)}
										plugSelected={this.state.plugSelected} 
									/>
							</div>
							<div className="col-sm-10">
								<PlugDetails
									plugSelected={this.state.plugSelected} />
									<br></br>
									<div id="power_display" className="mw-100 p-3 mh-100" style={{width:'800px', height:'300px'}} ></div>		
							</div>
					</div>
						<div>
							<a href="groups.html">To the groups</a>
						</div>	
				
			</div>
					

		);
	}
}

window.IoTHubApp = IoTHubApp;
