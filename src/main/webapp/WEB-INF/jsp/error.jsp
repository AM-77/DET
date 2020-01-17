<jsp:include page="./header.jsp" />
<section class="error-container">
        <div class="img">
            <img src="${contextPath}/assets/images/not_found.png" /> 
        </div>
        <div class="error">
            <h1>404</h1>
            <div>
                <p>The page you are looking for does not exist or has been moved.</p>
                <a href="${contextPath}">Go Home <span><img src="${contextPath}/assets/images/arrowhead-right.svg" alt="go home" /><span></a>
            </div>
        </div>
</section>
<jsp:include page="./footer.jsp" />