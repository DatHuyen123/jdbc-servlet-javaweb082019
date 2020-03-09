<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@include file="/common/taglib.jsp" %>
<c:url var = 'customerAPI' value="/api-customer" />
<c:url var="customerURL" value="/admin-customer?action=LIST" />
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
                    <a href="#">Danh Sách Khách Hàng</a>
                </li>
                <li>
                    Danh Sách Khách Hàng
                </li>
            </ul><!-- /.breadcrumb -->
        </div>
        <div class="page-content">
            <div class="row" >
                <form class="col-xs-12" id="formSubmit" action="${customerURL}" method="get" >

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
                                        <div class="col-sm-4">
                                            <label><b>Tên Khách Hàng: </b></label>
                                            <div class="fg-line">
                                                <input type="text" class="form-control input-sm" name="fullName" value="${model.fullName}"/>
                                            </div>
                                        </div>
                                        <div class="col-sm-4">
                                            <label><b>Email: </b></label>
                                            <div class="fg-line">
                                                <input type="text" class="form-control input-sm" name="email" value="${model.email}"/>
                                            </div>
                                        </div>
                                        <div class="col-sm-4">
                                            <label><b>Số Điện Thoại: </b></label>
                                            <div class="fg-line">
                                                <input type="text" class="form-control input-sm" name="phone" value="${model.phone}"/>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <div class="col-sm-5">
                                            <label><b>Nhân Viên Quản Lý: </b></label><br>
                                            <div class="fg-line col-sm-6">
                                                <div style="font-family: arial">
                                                    <select class="form-control " name="">
                                                        <option value="">---Chon Nhân Viên Quản Lý---</option>
                                                        <%--<c:forEach var="item" items="${districts}">
                                                            <option value="${item.key}" ${item.key == model.district ? 'selected' : ''}>${item.value}</option>
                                                        </c:forEach>--%>
                                                    </select>
                                                </div>
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
                                   title="Thêm Khách Hàng"
                                   href="<c:url value="/admin-customer?action=EDIT"/>">
                                    <span>
                                        <i class="fa fa-plus-circle bigger-110 purple"></i>
                                    </span>
                                </a>
                                <button type="button" id="btnDelete"
                                        class="dt-button buttons-colvis btn btn-white btn-primary btn-bold"
                                        data-toggle="tooltip"
                                        title="Xóa Khách Hàng"
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
            <%--Table--%>
        </div>

            <div class="row" >
                <div class="col-xs-12">
                    <table class="table table-bordered">
                        <thead>
                        <tr>
                            <th><input type="checkbox" value="" name="" id="checkAll"></th>
                            <th>Tên Khách Hàng</th>
                            <th>Địa Chỉ Email</th>
                            <th>Số Điện Thoại</th>
                            <th>Tên Công Ty</th>
                            <th>Nhu Cầu Khách Hàng</th>
                            <th>Ghi Chú</th>
                            <th>Trạng Thái</th>
                            <th>Thao Tac</th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach var="item" items="${model.listResult}">
                            <tr>
                                <td><input type="checkbox" value="${item.id}" name="" id="checkBox_${item.id}"></td>
                                <td>${item.fullName}</td>
                                <td>${item.email}</td>
                                <td>${item.phone}</td>
                                <td>${item.companyName}</td>
                                <td>${item.demand}</td>
                                <td>${item.note}</td>
                                <td>
                                    <c:if test="${item.status == 1}">
                                        Đang Xử Lý...
                                    </c:if>
                                    <c:if test="${item.status == 0}">
                                        Đã Xử Lý
                                    </c:if>
                                </td>
                                <td>
                                    <a class="btn btn-xs btn-primary btn-edit"
                                       data-toggle="tooltip"
                                       title="Cap nhat Khách Hàng" href="<c:url value="/admin-customer?action=EDIT&id=${item.id}"/>" >
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
<!-- /.main-content -->
<script type="text/javascript">

    $('#btnSearch').click(function (){
        $('#page').val(1);
        $('#maxPageItem').val(10);
        /* $('#sortName').val('fullname');
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
                window.location.href = "${customerURL}&message=delete_success&page=1&maxPageItem=10";
            },
            error: function() {
                window.location.href = "${customerURL}&message=error_system&page=1&maxPageItem=10";
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
                    $('#sortName').val('fullname');
                    $('#sortBy').val('ASC');
                    $('#formSubmit').submit();
                }
            }
        });
    });
</script>
</body>
</html>