<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<div class="container-fluid">

	<!-- Page Heading -->
	<div class="d-sm-flex align-items-center justify-content-between mb-4">
		<h1 class="h3 mb-0 text-gray-800">Cards</h1>
	</div>

	<div class="row">
		<form:form class="form-horizontal" action="save" method="post"
			modelAttribute="employee">
			<fieldset>

				<c:if test="${not empty success}">
					<div class="alert alert-success fade show">
						<strong>Success!</strong> ${success}
					</div>
				</c:if>
				<c:if test="${not empty error}">
					<div class="alert alert-danger fade show">
						<strong>Ohh!</strong> ${error}
					</div>
				</c:if>


				<!-- Form Name -->
				<legend>Form Name</legend>
				<form:hidden path="id" />
				<!-- Text input-->
				<div class="form-group">
					<label class="col-md-12 control-label" for="emp_name">Enter
						Name</label>
					<div class="col-md-8">
						<form:input id="emp_name" path="emp_name" type="text"
							placeholder="Enter Name" class="form-control input-lg" />
						<form:errors path="emp_name" />
					</div>
				</div>

				<!-- Button -->
				<div class="form-group">
					<label class="col-md-12 control-label" for=""></label>
					<div class="col-md-12">
						<button id="" name="" class="btn btn-primary">Save</button>
					</div>
				</div>

			</fieldset>
		</form:form>

		<!-- DataTales Example -->
		<div class="card shadow mb-4">
			<div class="card-header py-3">
				<h6 class="m-0 font-weight-bold text-primary">DataTables
					Example</h6>
			</div>
			<div class="card-body">
				<div class="table-responsive">
					<table class="table table-bordered" id="dataTable" width="100%"
						cellspacing="0">
						<tr>
							<th>Sr. No</th>
							<th>Sr. No</th>
							<th>ID</th>
							<th>NAME</th>
							<th>Edit</th>
							<th>Delete</th>
						</tr>
						<c:forEach items="${employees}" var="employee" varStatus="row">
							<tr>
								<td>${row.index}</td>
								<td>${row.count}</td>
								<td>${employee.id}</td>
								<td>${employee.emp_name}</td>
								<td><a href="?id=${employee.id}">Edit</a></td>
								<td><a href="delete?id=${employee.id}">Delete</a></td>
							</tr>
						</c:forEach>
					</table>
				</div>
			</div>
		</div>
	</div>
</div>
<!-- /.container-fluid -->