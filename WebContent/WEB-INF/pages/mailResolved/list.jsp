<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>


<div class="row-fluid">
	<div class="span12">
		<div class="box">
			<div class="box-title" style="margin-bottom:10px;">
				<h3>
					<i class="icon-table"></i> 邮件账户
				</h3>
			</div>

			<div class="box-content nopadding">
				<div id="DataTables_Table_1_wrapper" class="dataTables_wrapper" role="grid">
					<table id="DataTables_Table_1" class="table table-hover table-nomargin table-bordered dataTable-columnfilter dataTable"
						aria-describedby="DataTables_Table_1_info">
						<thead>
							<tr role="row">
								<th><input type="checkbox" /></th>
								<th>账户名</th>
								<th>host</th>
								<th>protocol</th>
								<th>port</th>
								<th>来源</th>
							</tr>
							
						</thead>
						<tbody role="alert" aria-live="polite" aria-relevant="all">
							<c:forEach var="account" items="${accounts }">
							<tr class="odd">
								<td><input type="checkbox" /></td>
								<td>${account.username }</td>
								<td>${account.host }</td>
								<td>${account.protocol }</td>
								<td>${account.port }</td>
								<td>${account.source }</td>
							</tr>
							</c:forEach>
						</tbody>
					</table>	
				</div>
			</div>
		</div>
	</div>
</div>

