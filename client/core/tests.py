from urllib import response
from django.test import TestCase, SimpleTestCase
from django.contrib.auth.models import User
from django.contrib.auth.hashers import make_password
from rest_framework.test import APITestCase
from django.urls import resolve
from .views import *
from django.urls import resolve, reverse

# Create your tests here.


class LoginTests(APITestCase):
    
    def setUser(self):
    
        user = User.objects.create(
            username='admin',
            email='admin@admin.com',
            password=make_password('admin')
        )
        self.client.user = user

    def test_getting_post(self):
        """test the get method"""
        # get url localhost:8000/
        response1 = self.client.get('/core/posts/')
        print("Core response", response1)
        # check which template is used
        # self.assertTemplateUsed(response, '')

        # check response status is equal to 200
        self.assertEqual(response1.status_code, 200)

class TestUrls(TestCase):
    """Class used for testing URLS"""

    def test_get_index(self):
        """test the get method"""
        # get url localhost:8000/
        response = self.client.get('/admin/login/?next=/admin/')
        print("here", response)
        # check which template is used
        # self.assertTemplateUsed(response, '')

        # check response status is equal to 200
        self.assertEqual(response.status_code, 200)

    
    def test_auth(self):
        """test the get method"""
        # get url localhost:8000/
        response = self.client.get('/token-auth/')
        print("token-auth", response)
        # check which template is used
        # self.assertTemplateUsed(response, '')

        # check response status is equal to 405
        self.assertEqual(response.status_code, 405)

    





