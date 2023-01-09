from django.urls import path
from .views import current_user, UserList
from . import views

from django.urls import path, include
from rest_framework import routers

from .views import get_file

urlpatterns = [
    path('current_user/', current_user),
    path('get_file/', get_file),
    path('users/', UserList.as_view()),
    path('posts/', views.PostView.as_view(), name= 'posts_list'),
]