const btnClassAdd = "btn btn-primary btn-block";
const btnClassDel = "btn btn-danger btn-block";


	
function MemberView(props) {
	let current = {};
	
	current = props.allGroups.filter(val => (props.groupSelected.name === val.name ))[0];
	React.useEffect(() => {
		props.updateGroupSelected(current)
		
	}, [current, props.updateGroupSelected])
	if(props.groupSelected!=null){
		var rows = props.groupSelected.members.map(function (member) {
			return (
				<div key={member.name}>
					<p>Name: {member.name} </p>
				    <p>State: {member.state} </p>
				    <p>Power: {member.power} </p>
				</div>
				);
		});

		return (
			<div>
				{rows}
				{props.groupSelected !== null && 
						  <div style={{display: "flex", justifyContent: "space-around", maxWidth: "350px", paddingLeft: "90px",paddingBottom: "10px"}}>
			<button className="btn col-sm-5 btn-warning" onClick={() => props.groupAction(props.groupSelected, "on")}>
				Switch On
			</button>
			<div className="col-sm-6"></div>
			<button className="btn col-sm-5 btn-warning" onClick={() => props.groupAction(props.groupSelected, "off")}>
				Switch Off
			</button>
			<div className="col-sm-6"></div>
				<button className="btn col-sm-5 btn-warning" onClick={() => props.groupAction(props.groupSelected, "toggle")}>
					Toggle
				</button>
			</div>}
			</div>);
	}	


	return (
	<div>Please select a group</div>
	);
    
}

function GetName(group, props) {
	if(props.groupSelected!=null){
		if(props.groupSelected.name==group.name){
			return "btn-block btn-warning"
		}
	}
   return "btn-block btn-primary"
}

 
function GroupsList(props) {
	return ( <div className="row">
		<div style={{ display: "flex", justifyContent: "space-between"}}>
			<div style={{ display: "flex", flexDirection: "column", paddingTop: "16px", maxWidth: "250px"}}>
			   {props.allGroups.map((group, index) => 
			 <button onClick={() => props.updateGroupSelected(group)} 
			 className={GetName(group, props)} 
			 key={index} style={{maxWidth: "250px"}}>
				{group.name} 
			 </button>
			  )}
		 </div>
				   {props.groupSelected&&  
				   <div className="col-sm-16" style={{ paddingLeft: "35px"}}> 
				       
					 <MemberView {...props}
					 groupSelected={props.groupSelected} />
					 </div>}

	  </div>
			</div>);
}

 function changeStateGroup(groupS, gAction) {
	return (
		<div className="row">
			<button className="btn col-sm-5 btn-warning" onClick={() => gAction(groupS, "on")}>
				Switch On
			</button>
			<div className="col-sm-6"></div>
			<button className="btn col-sm-5 btn-warning" onClick={() => gAction(groupS, "off")}>
				Switch Off
			</button>
			<div className="col-sm-6"></div>
				<button className="btn col-sm-5 btn-warning" onClick={() => gAction(groupS, "toggle")}>
					Toggle
				</button>
			</div>
		);
	}
			
		
	

/**
 * This is a stateless view showing inputs for add/replace groups.
 */
function AddGroup(props) {
	var onChangeName = event => props.onInputNameChange(event.target.value);
    var onChangeMembers = event => props.onInputMembersChange(event.target.value);
    return (
		<div>
			<label>Group Name</label>
			<input type="text" onChange={onChangeName} value={props.inputName}/>
			<label>Members</label>
			<input type="text" onChange={onChangeMembers} value={props.inputMembers}
				size="60" placeholder="e.g. a,b,c"/>
			<button className="btn btn-primary" onClick={props.onAddGroup}>
				Add/Replace</button>
		</div>
	);
}



/**
 * This is a stateless view showing the Group view
 */
function GroupsView(props) {
	console.info(props);
return (
		<div className="container">
			<div className="row">
			  {props.members.get_group_names().length ? "Enter Groups": "There are no groups."}
				
			</div>
			<div className="row">
				<div className="col-sm-12">
				    <AddGroup {...props} /> 
				</div>
			</div>	
				<div className="row">
				   <div className="col-sm-4">
						<GroupsList 
						groupNames={props.members.get_group_names()} 
						members={props.members.get_group_members()}
						updateGroupSelected={props.updateGroupSelected}
						groupSelected={props.groupSelected}
						inputName={props.inputName} 
						allGroups={props.allGroups}
						inputMembers={props.inputMembers}
						onDeleteGroup={props.onDeleteGroup}
					    groupAction={props.groupAction}
						
						/>
						<div>
							
						{(props.groupSelected!=null)?<button className="btn btn-block btn-secondary btn-sm" onClick={() => props.onDeleteGroup(props.groupSelected.name)}>
							Delete group
					     </button>:<button className="btn btn-block btn-secondary btn-sm disabled">Delete group</button>}

						</div>
					    
					
		
				</div>
				   
		   </div>
		</div>);
}

	

       
        
		
window.MemberView = MemberView;
window.GroupsView=GroupsView;