class Groups extends React.Component {

	constructor(props) {
		super(props);
		this.state = {
			plugs: [],
			groups: [],
			showPlugs: true,
			AMG : { // AMG for Add Modify Groups
				isHidden : true, 
				inputName : undefined,
				inputMembers : undefined
			}
		};
    }
    updateGroups(groups) {
		if (!Array.isArray(groups)) {
			console.debug("Groups: cannot get groups " + JSON.stringify(groups));
			return;
		}

		console.debug("Groups: " + JSON.stringify(groups));
		this.setState({ groups: groups });

		if (this.props.groupSelected == null)
			return;

		// notify parent
		for (var i = 0; i < groups.length; ++i) {
			if (this.props.groupSelected.name == groups[i].name) {
				this.props.updateGroupSelected(groups[i]);
				return;
			}
		}
    }
    getGroups() {
		fetch("../api/groups")
		.then(rsp => rsp.json())
		.then(data => this.updateGroups(data))
		.catch(err => console.debug("Groups: error " + JSON.stringify(err)));
    }
    createGroup = (groupName, groupMembers) => {
		console.info("RESTful: create group "+groupName
			+" "+JSON.stringify(groupMembers));
		
		var postReq = {
			method: "POST",
			headers: {"Content-Type": "application/json"},
			body: JSON.stringify(groupMembers)
		};
		fetch("api/groups/"+groupName, postReq)
			.then(rsp => this.getGroups())
			.catch(err => console.error("Members: createGroup", err));
	}
    componentDidMount() {
		this.getGroups();
		window.setInterval(() => this.getGroups(), 1000);
    }
    render() {

		var arenomer = (group) => {
			if (this.props.groupSelected==null){
				return "btn btn-block btn-outline-warning"
			}
			else if(this.props.groupSelected.name==group.name){
					return "btn btn-block btn-info"
			}
			else{
				return "btn btn-block btn-outline-warning"
			}
        }
        var handleGroupsList = () => {
			if (this.state.groups.length == 0){
					return (<div>There are no groups.</div>);
			}
			else{
				return this.state.groups.map((group) => {
					return (
						<div className="container">
							<p>
								<button className={arenomer(group)} onClick={() => {this.props.updateGroupSelected(group); this.props.updateSelectedPlugOfGroup(null) }}>{group.name}</button>
                                </p>
						</div>
					);
				});
			}
		}

		var plugsOfSelectedGroup = () => {
			if (this.props.groupSelected != null){
				return (
					<div className="row">
						<div className="col-sm-3">
							<PlugsView
							plugs={this.props.groupSelected.members}
                            plugSelected={this.props.selectedPlugOfGroup}
                            selectPlug={this.props.updateSelectedPlugOfGroup} />
							{/* <p>{(this.props.selectedPlugOfGroup==null)?"c'est null":this.props.selectedPlugOfGroup.name}</p> */}
						</div>
						<div className="col-sm-9">
							<PlugDetails plugSelected={this.props.selectedPlugOfGroup} />
						</div>
					</div>
				);
            }
            return (
				<div>
					{"Please, select a group"}
				</div>)
		}
        var onChangeName = (event) =>{
			console.debug("Members: onInputNameChange", event.target.value);
			this.setState({AMG : {...this.state.AMG, inputName: event.target.value}});
		}

		var onChangeMembers = (event) =>{
			console.debug("Members: onInputMembersChange", event.target.value);
			this.setState({AMG : {...this.state.AMG, inputMembers: event.target.value}});
        }
        var onAddGroup = () => {
			var name = this.state.AMG.inputName;
			var members = this.state.AMG.inputMembers.split(',');
		
			this.createGroup(name, members);
			// this.setState({AMG : {...this.state.AMG, isHidden:true}})
			this.setState({AMG : {isHidden:true, inputMembers: "", inputName: ""}})
		}
        var deleteThisGroup = (groupName) => {
			console.info("RESTful: delete group "+groupName);
		
			var delReq = {
				method: "DELETE"
			};
			fetch("api/groups/"+groupName, delReq)
				.then(rsp => this.getGroups())
				.catch(err => console.error("Members: deleteGroup", err));

			//////////////////////////////////////////////////////////////////////////////////////
			this.props.updateGroupSelected(null)
        }
        var tryDeleteGroup = () => {
			if (this.props.groupSelected != null){
				deleteThisGroup(this.props.groupSelected.name)
			}
        }
        var AddModifyGroup = () =>{
			return (
				<div>
					<button className="btn btn-block btn-secondary btn-sm" onClick={() => {this.setState({AMG : {...this.state.AMG, isHidden:!this.state.AMG.isHidden}})}}>Add/Modify group</button>
					<div hidden={this.state.AMG.isHidden}>
						<p>bonobo</p>
						<label>Group Name</label>
						<input type="text" onChange={onChangeName} value={this.state.AMG.inputName}/>
                        <label>Members</label>
						<input type="text" onChange={onChangeMembers} value={this.state.AMG.inputMembers} placeholder="e.g. a,b.100,cc"/>
						<button className="btn btn-primary" onClick={onAddGroup}>Add/Replace</button>
					</div>
					<p></p>
                    {(this.props.groupSelected!=null)?<button className="btn btn-block btn-secondary btn-sm" onClick={tryDeleteGroup}>Delete group</button>:<button className="btn btn-block btn-secondary btn-sm disabled" aria-disabled={true} data-toggle="tooltip" data-placement="bottom" title="Select a group to delete">Delete group</button>}
					{/* <button className="btn btn-block btn-secondary btn-sm" onClick={tryDeleteGroup}>Delete group</button> */}
				</div>
			)
		}
        var groupAction = (group, action) => {
			//todo
			group.members.map(function (member){
				var name = member.name
				var url = "../api/plugs/" + name + "?action=" + action;
				console.info("PlugDetails: request " + url);
				fetch(url);
			});
        }
        var changeStateGroup = () => {
			return (
				<div className="row col-sm-9">
					<button className="btn col-sm-3 btn-warning" onClick={() => groupAction(this.props.groupSelected, "on")}>
						Switch On
					</button>
					<div className="col-sm-1"></div>
					<button className="btn col-sm-3 btn-warning" onClick={() => groupAction(this.props.groupSelected, "off")}>
						Switch Off
                        </button>
					<div className="col-sm-1"></div>
					<button className="btn col-sm-3 btn-warning" onClick={() => groupAction(this.props.groupSelected, "toggle")}>
						Toogle
					</button>
				</div>
			);
        }
        return (
			<div className="row">
				<div className="col-sm-2">
					{handleGroupsList()}
					{AddModifyGroup()}
				</div>
				<div className="col-sm-10">
                    {/* {(this.props.groupSelected!=null)?"You are seing: "+this.props.groupSelected.name:undefined}
						<p></p> */}
						{(this.props.groupSelected!=null)?changeStateGroup(this.props.groupSelected):undefined}
						<p></p>
						{(this.props.groupSelected!=null)?this.props.groupSelected.name+"'s Plugs: ":undefined}
						<p></p>
						{plugsOfSelectedGroup(this.props)}
				</div>
			</div>
            );
        }
    }
    
    window.Groups = Groups;
    

                
                            
                            