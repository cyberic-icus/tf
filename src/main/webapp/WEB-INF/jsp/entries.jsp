<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>


<!DOCTYPE html>
<html>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.2/dist/umd/popper.min.js" integrity="sha384-IQsoLXl5PILFhosVNubq5LC7Qb9DXgDA9i+tQ8Zj3iwWAwPtgFTxbJ8NT4GN1R8p" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/js/bootstrap.min.js" integrity="sha384-Atwg2Pkwv9vp0ygtn1JAojH0nYbwNJLPhwyoVbhoPwBhjQPR5VtM2+xf0Uwh9KtT" crossorigin="anonymous"></script>

<head>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-+0n0xVW2eSR5OomGNYDnhzAbDsOXxcvSN1TPprVMTNDbiYZCxYbOOl7+AMvyTG2x" crossorigin="anonymous">
    <meta charset="utf-8">
    <title>Ваши записи</title>
</head>
<body>
<div class="row mb-3 align-items-center text-center d-flex justify-content-center">
  <table class="table table-responsive text-center mx-auto w-auto">
      <thead>
        <tr>
          <th scope="col">#</th>
          <th scope="col">Название записи</th>
          <th scope="col">Тело записи</th>
          <th scope="col">Действия</th>
        </tr>
      </thead>
      <tbody>
       <c:forEach items="${getListEntries}" var="entry">
        <tr>
          <th scope="row">${entry.id}</th>
          <td class="overflow-auto">${entry.header}</td>
          <td class="overflow-auto" >${entry.body}</td>
          <td class="btn-group">

         <form action="/entries" method="post" class="btn btn-secondary">
                             <input type="hidden" name="EntryId" value="${entry.id}"/>
                             <input type="hidden" name="action" value="delete"/>
                             <button type="submit">Delete</button>
                           </form>


          </td>

        </tr>
        </c:forEach>
      </tbody>
    </table>
  <a href="/entry">Добавить запись</a>
  <a href="/">Главная</a>
</div>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.2/dist/umd/popper.min.js" integrity="sha384-IQsoLXl5PILFhosVNubq5LC7Qb9DXgDA9i+tQ8Zj3iwWAwPtgFTxbJ8NT4GN1R8p" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/js/bootstrap.min.js" integrity="sha384-Atwg2Pkwv9vp0ygtn1JAojH0nYbwNJLPhwyoVbhoPwBhjQPR5VtM2+xf0Uwh9KtT" crossorigin="anonymous"></script>
</body>
</html>