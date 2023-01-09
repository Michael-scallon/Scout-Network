
from __future__ import unicode_literals
from django.db import models
import os



class File(models.Model):
    image = models.FileField(null=True,blank=True,upload_to='media/documents/')
    


class Post(models.Model):
    title = models.CharField(max_length=100)
    content = models.TextField()
    image = models.FileField(upload_to="content")
    
    def __str__(self):
        return self.title