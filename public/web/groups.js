
/**
 * A model for managing members in groups.
 */
function create_members_model(groups) {
	// create the data structure
	var all_members = new Set(); // all unique member names
	var group_names = [];
	var group_members = new Map(); // group_name to set of group members 
	for (var group of groups) {
		group_names.push(group.name);
		var members = new Set(group.members);
		group_members.set(group.name, members);
		members.forEach(member => all_members.add(member));
	}

	var member_names = Array.from(all_members); //this has all the members combned i.e from all groups
	group_names.sort();
	member_names.sort();

	// create the object
	var that = {}
	that.get_group_names = () => group_names;
	that.get_member_names = () => member_names;
	that.is_member_in_group = (member_name, group_name) =>
		!group_members.has(group_name) ? false :
			group_members.get(group_name).has(member_name);
	that.get_group_members = group_name => group_members.get(group_name);

	return that;
}

/**
 * The Members controller holds the state of groups.
 * It creates its view in render().
 */
class Members extends React.Component {

	constructor(props) {
		super(props);
		console.info("Members constructor()", props);
		this.state = {
			members: create_members_model([]),
			inputName: "",
			inputMembers: "",
			allGroups: [{}]
		};
	}

	componentDidMount() {
		console.info("Members componentDidMount()");
		this.getGroups();
		setInterval(this.getGroups, 1000); // for uodating the groups every second
	}

	render() {
		console.info("----------------",this.props.groupSelected);
		return (< GroupsView
			members={this.state.members}
			allGroups={this.state.allGroups}
			inputName={this.state.inputName} inputMembers={this.state.inputMembers}
			onMemberChange={this.onMemberChange}
			onDeleteGroup={this.onDeleteGroup}
			onInputNameChange={this.onInputNameChange}
			onInputMembersChange={this.onInputMembersChange}
			onAddGroup={this.onAddGroup}
			onAddMemberToAllGroups={this.onAddMemberToAllGroups}
			updateGroupSelected={this.props.updateGroupSelected}
			groupSelected={this.props.groupSelected}
			groupAction={this.groupAction}
			 
		/>);
	}

	getGroups = () => {
		console.debug("RESTful: get groups");
		fetch("api/groups")
			.then(rsp => rsp.json())
			.then(groups => this.showGroups(groups))
			.catch(err => console.error("Members: getGroups", err));
	}

	showGroups = groups => {
		this.setState({
			members: create_members_model(groups),
			allGroups: groups
		});
	}

	createGroup = (groupName, groupMembers) => {
		console.info("RESTful: create group " + groupName
			+ " " + JSON.stringify(groupMembers));

		var postReq = {
			method: "POST",
			headers: { "Content-Type": "application/json" },
			body: JSON.stringify(groupMembers)
		};
		fetch("api/groups/" + groupName, postReq)
			.then(rsp => this.getGroups())
			.catch(err => console.error("Members: createGroup", err));
	}

	deleteGroup = groupName => {
		console.info("RESTful: delete group " + groupName);

		var delReq = {
			method: "DELETE"
		};
		fetch("api/groups/" + groupName, delReq)
			.then(rsp => this.getGroups())
			.catch(err => console.error("Members: deleteGroup", err));
	}

	onMemberChange = (memberName, groupName) => {
		var groupMembers = new Set(this.state.members.get_group_members(groupName));
		if (groupMembers.has(memberName))
			groupMembers.delete(memberName);
		else
			groupMembers.add(memberName);

		this.createGroup(groupName, Array.from(groupMembers));
	}

	onDeleteGroup = groupName => {
		this.deleteGroup(groupName);
	}

	onInputNameChange = value => {
		console.debug("Members: onInputNameChange", value);
		this.setState({ inputName: value });
	}

	onInputMembersChange = value => {
		console.debug("Members: onInputMembersChange", value);
		this.setState({ inputMembers: value });
	}

	onAddGroup = () => {
		var name = this.state.inputName;
		var members = this.state.inputMembers.split(',');

		this.createGroup(name, members);     //here are members
	}
	
    //for the entire group action
	groupAction = (group, action) => {
		//todo
		group.members.map(function (member) {
			var name = member.name
			var url = "../api/plugs/" + name + "?action=" + action;
			console.info("PlugDetails: request " + url);
			fetch(url).then(res => res.json()).then(res => getGroups());
		});
	}

}

// export
window.Members = Members;

