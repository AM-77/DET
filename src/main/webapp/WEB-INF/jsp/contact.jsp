<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<jsp:include page="./header.jsp" />
<div class="contact-container">
    <div class="head">
        <h1>contact us</h1>
        <p>if you have somthing in your mind we'd love to hear from you</p>
    </div>
    <div class="content">
        <div class="map">
            <h3>Our Location</h3>
            <div class="the-map">
                <iframe src="https://www.google.com/maps/embed?pb=!1m14!1m8!1m3!1d12814.438343435428!2d2.912026!3d36.587625!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x0%3A0x250cef391f78e898!2sSERMO+Machines!5e0!3m2!1sen!2sdz!4v1497109523657" allowfullscreen ></iframe>
            </div>
        </div>
        <div class="form">
            <h3>E-Mail Us</h3>
            <span class="confirm-message"></span>
            <form>
                <div class="form-head">
                    <div class="name">
                        <label for="name">Your Name:</label>
                        <input id="name" name="name" type="text" placeholder="Your Name Please" />
                    </div>
                    <div class="email">
                        <label for="email">Your E-Mail:</label>
                        <input id="email" name="email" type="text" placeholder="Your E-Mail Please" />
                    </div>
                </div>
                <div class="message">
                    <label for="message">Your Message:</label>
                    <textarea id="message" name="message" type="text" placeholder="Your Message please"></textarea>
                </div>
                <button class="send">send <span></span></button>
            </form>
        </div>
    </div>
</div>
<jsp:include page="./footer.jsp" />