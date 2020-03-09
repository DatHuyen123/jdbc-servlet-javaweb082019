<%--
  Created by IntelliJ IDEA.
  User: DANG VAN DAT
  Date: 8/31/2019
  Time: 12:48 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@include file="/common/taglib.jsp" %>
<c:url var = 'buildingAPI' value="/api-building" />
<c:url var="buildingURL" value="/admin-building?action=LIST" />
<html>
<head>
    <title>Danh Sách Tòa Nhà</title>
</head>
<body>
<div class="main-content">
    <div class="main-content-inner">
        <div class="breadcrumbs ace-save-state" id="breadcrumbs">
            <ul class="breadcrumb">
                <li>
                    <i class="ace-icon fa fa-home home-icon"></i>
                    <a href="#">Danh Sách Tòa Nhà</a>
                </li>
                <li>
                    Danh Sách Tòa Nhà
                </li>
            </ul><!-- /.breadcrumb -->
        </div>
        <div class="page-content">
            <div class="row" >
                <form class="col-xs-12" id="formSubmit" action="${buildingURL}" method="get" >

                    <%-- start form --%>

                    <%--search box--%>

                    <div class="widget-box table-filter">
                        <div class="widget-header">
                            <h4 class="widget-title">
                                Tìm Kiếm
                            </h4>
                            <div class="widget-toolbar">
                                <a href="#" data-action="collapse"><i class="ace-icon fa fa-chevron-up"></i></a>
                            </div>
                        </div>
                        <div class="widget-body">
                            <div class="widget-main">
                                <div class="form-horizontal">
                                    <div class="form-group">
                                        <div class="col-sm-6">
                                            <label><b>Tên Tòa Nhà</b></label>
                                            <div class="fg-line">
                                                <input type="text" class="form-control input-sm" name="name" value="${model.name}"/>
                                            </div>
                                        </div>
                                        <div class="col-sm-6">
                                            <label><b>Diện Tích Sàn</b></label>
                                            <div class="fg-line">
                                                <input type="text" class="form-control input-sm" name="buildingArea" value="${model.buildingArea}"/>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <div class="col-sm-4">
                                            <label><b>Quận: </b></label><br>
                                            <div class="fg-line col-sm-6">
                                                <div style="font-family: arial">
                                                <select class="form-control " name="district">
                                                    <option value="">---Chon Quan---</option>
                                                    <c:forEach var="item" items="${districts}">
                                                        <option value="${item.key}" ${item.key == model.district ? 'selected' : ''}>${item.value}</option>
                                                    </c:forEach>
                                                </select>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="col-sm-4">
                                            <label><b>Phường</b></label>
                                            <div class="fg-line">
                                                <input type="text" class="form-control input-sm" name="ward" value="${model.ward}"/>
                                            </div>
                                        </div>
                                        <div class="col-sm-4">
                                            <label><b>Đường</b></label>
                                            <div class="fg-line">
                                                <input type="text" class="form-control input-sm" name="street" value="${model.street}"/>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <div class="col-sm-4">
                                            <label><b>Số Tầng Hầm</b></label>
                                            <div class="fg-line">
                                                <input type="text" class="form-control input-sm" name="numberOfBasement" value="${model.numberOfBasement}"/>
                                            </div>
                                        </div>
                                        <div class="col-sm-4">
                                            <label><b>Hướng</b></label>
                                            <div class="fg-line">
                                                <input type="text" class="form-control input-sm" name="direction" value="${model.direction}"/>
                                            </div>
                                        </div>
                                        <div class="col-sm-4">
                                            <label><b>Hạng</b></label>
                                            <div class="fg-line">
                                                <input type="text" class="form-control input-sm" name="levelBuilding" value="${model.levelBuilding}"/>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <div class="col-sm-3">
                                            <label><b>Diện Tích Từ</b></label>
                                            <div class="fg-line">
                                                <input type="text" class="form-control input-sm" name="areaRentFrom" value="${model.areaRentFrom}"/>
                                            </div>
                                        </div>
                                        <div class="col-sm-3">
                                            <label><b>Diện Tích Đến</b></label>
                                            <div class="fg-line">
                                                <input type="text" class="form-control input-sm" name="areaRentTo" value="${model.areaRentTo}"/>
                                            </div>
                                        </div>
                                        <div class="col-sm-3">
                                            <label><b>Giá Thuê Từ</b></label>
                                            <div class="fg-line">
                                                <input type="text" class="form-control input-sm" name="cosTrentFrom" value="${model.cosTrentFrom}"/>
                                            </div>
                                        </div>
                                        <div class="col-sm-3">
                                            <label><b>Giá Thuê Đến</b></label>
                                            <div class="fg-line">
                                                <input type="text" class="form-control input-sm" name="cosTrentTo" value="${model.cosTrentTo}"/>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <div class="col-sm-4">
                                            <label><b>Tên Quản Lý</b></label>
                                            <div class="fg-line">
                                                <input type="text" class="form-control input-sm" name="managerName" value="${model.managerName}"/>
                                            </div>
                                        </div>
                                        <div class="col-sm-4">
                                            <label><b>Điện Thoại Quản Lý</b></label>
                                            <div class="fg-line">
                                                <input type="text" class="form-control input-sm" name="managerPhone" value="${model.managerPhone}"/>
                                            </div>
                                        </div>
                                        <div class="col-sm-4">
                                            <label><b>Chọn Nhân Viên Phụ Trách</b></label><br>
                                            <div class="fg-line col-sm-8">
                                                <select class="form-control" id="seli">
                                                    <option value="">---Chọn Nhân Viên Phụ Trách---</option>
                                                    <option value="">1</option>
                                                </select>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <div class="col-sm-6">
                                            <label><b>Loại Tòa Nhà</b></label>
                                            <div class="fg-line">
                                                <c:forEach var="item" items="${buildingTypes}">
                                                    <label class="checkbox-inline"><input type="checkbox" value="${item.key}" ${fn:contains(fn:join(model.buildingTypes , ','), item.key) ? 'checked' :''} name="buildingTypes"><b>${item.value}</b></label>
                                                </c:forEach>
                                            </div>
                                        </div>
                                    </div>
                                    <input type="hidden" name="action" value="LIST"/>
                                    <div class="form-group">
                                        <div class="col-sm-6">
                                            <button id = "btnSearch" type="button" class="btn btn-sm btn-success">
                                                Tìm Kiếm
                                                <i class="ace-icon fa fa-arrow-right icon-on-right bigger-110"></i>
                                            </button>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <input type="hidden" value="1" id="page" name="page" />
                    <input type="hidden" value="${model.maxPageItem}" id="maxPageItem" name="maxPageItem" />
                    <input type="hidden" value="" id="sortName" name="sortName" />
                    <input type="hidden" value="" id="sortBy" name="sortBy" />
                    <%-- end form --%>
                </form>
                    <%-- end group --%>
                        <div class="col-xs-12">
                            <div class="table-btn-controls">
                                <div class="pull-right tableTools-container">
                                    <div class="dt-buttons btn-overlap btn-group">
                                        <a flag="info"
                                           class="dt-button buttons-colvis btn btn-white btn-primary btn-bold"
                                           data-toggle="tooltip"
                                           title="Thêm Tòa Nhà"
                                           href="<c:url value="/admin-building?action=EDIT"/>">
                                    <span>
                                        <i class="fa fa-plus-circle bigger-110 purple"></i>
                                    </span>
                                        </a>
                                        <button type="button" id="btnDelete"
                                                class="dt-button buttons-colvis btn btn-white btn-primary btn-bold"
                                                data-toggle="tooltip"
                                                title="Xóa Tòa Nhà"
                                        >
                                <span>
                                    <i class="fa fa-trash-o bigger-110 pink"></i>
                                </span>
                                        </button>
                                    </div>
                                </div>
                            </div>
                        </div>
                    <%-- button add , delete--%>

                </div>
            <%--Table--%>
            <div class="row" >
                <div class="col-xs-12">
                    <table class="table table-bordered">
                        <thead>
                        <tr>
                            <th><input type="checkbox" value="" name="" id="checkAll"></th>
                            <th>Tên Tòa Nhà</th>
                            <th>Địa Chỉ</th>
                            <th>Quận</th>
                            <th>Diện Tích Sàn</th>
                            <th>Số Tầng Hầm</th>
                            <th>Giá thuê</th>
                            <th>Diện Tích thuê</th>
                            <th>Loại Tòa Nhà</th>
                            <th>Tên Quản Lý</th>
                            <th>Hạng Tòa Nhà</th>
                            <th>Số Điện Thoại Quản Lý</th>
                            <th>Thao Tac</th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach var="item" items="${model.listResult}">
                            <tr>
                                <td><input type="checkbox" value="${item.id}" name="" id="checkBox_${item.id}"></td>
                                <td>${item.name}</td>
                                <td>${item.address}</td>
                                <td>${item.district}</td>
                                <td>${item.buildingArea}</td>
                                <td>${item.numberOfBasement}</td>
                                <td>${item.costRent}</td>
                                <td>${item.rentArea}</td>
                                <td>${item.type}</td>
                                <td>${item.managerName}</td>
                                <td>${item.levelBuilding}</td>
                                <td>${item.managerPhone}</td>
                                <td>
                                    <a class="btn btn-xs btn-primary btn-edit"
                                    data-toggle="tooltip"
                                    title="Cap nhat Toa Nha" href="<c:url value="/admin-building?action=EDIT&id=${item.id}"/>" >
                                        <i class="fa fa-pencil-square-o" aria-hidden="true"></i>
                                    </a>
                                </td>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>

                </div>
            </div>
            <div class="container">
                <ul id="pagination" class="pagination-lg pagination"></ul>
            </div>
            <div class="well text-center" id="content"></div>
            <%-- /Table --%>
            </div>
        </div>
    </div>
</div>
<!-- /.main-content -->
<script type="text/javascript">

    $('#btnSearch').click(function (){
        $('#page').val(1);
        $('#maxPageItem').val(10);
       /* $('#sortName').val('name');
        $('#sortBy').val('ASC');*/
        $('#formSubmit').submit();
    });

    $('#btnDelete').click(function (){
       var dataArr = $('tbody input[type=checkbox]:checked').map(function (){
           return $(this).val();
       }).get();
       var data = {};
       data['ids'] = dataArr;
       deleteBuilding(data);
    });
    
    function deleteBuilding(data) {
        $.ajax({
            url: '${buildingAPI}',
            data: JSON.stringify(data),
            type:'DELETE',
            contentType: 'application/json',
            success: function(data) {
                window.location.href = "${buildingURL}&message=delete_success&page=1&maxPageItem=10";
            },
            error: function() {
                window.location.href = "${buildingURL}&message=error_system&page=1&maxPageItem=10";
            }
        });
    }
    var totalPage = ${model.totalPage};
    var currentPage = ${model.page};
    $(function () {
        $('#pagination').twbsPagination({
            totalPages: totalPage,
            visiblePages: 5,
            startPage : currentPage,
            onPageClick: function (event, page) {
                //$('#content').text('page: ' + page);
                if(currentPage != page){
                    $('#page').val(page);
                    $('#maxPageItem').val(10);
                    $('#sortName').val('name');
                    $('#sortBy').val('ASC');
                    $('#formSubmit').submit();
                }
            }
        });
    });
</script>
</body>
</html>
