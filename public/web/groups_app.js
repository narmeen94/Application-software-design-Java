/**
 * The App class is a controller holding the global state.
 * It creates all children controllers in render().
 */


/**
 * The App class is a controller holding the global state.
 * It creates all children controllers in render().
 */
class GroupsApp extends React.Component {

	constructor(props) {
		super(props);
		console.info("MembersApp constructor()");
		this.state = {
			groupSelected: null
		};
	}

	updateGroupSelected= (group) => {
		console.info(group)
		this.setState({ groupSelected: group });
	}


	render() {
            console.info("MembersApp render()",this.props);
            
		return (
		<div className="container">
			<div className="row">
				<div className="col-sm-12">
				<h3>Welcome To IoT Hub Groups</h3>
				</div>
                
                
                <div className="col-sm-12">
				<Members
				updateGroupSelected={this.updateGroupSelected}
				groupSelected={this.state.groupSelected}/>

				</div>
                
                
			</div>
		</div>);
	}
}

window.GroupsApp = GroupsApp;
