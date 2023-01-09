from django.http import HttpResponseRedirect,HttpResponse
from django.contrib.auth.models import User
from rest_framework import permissions, status
from rest_framework.decorators import api_view
from rest_framework.response import Response
from rest_framework.views import APIView
from .serializers import UserSerializer, UserSerializerWithToken
from django.core.files.storage import FileSystemStorage
from rest_framework.viewsets import ViewSet
from django.http import JsonResponse
from .models import Post
from rest_framework.parsers import MultiPartParser, FormParser
from .serializers import PostSerializer

@api_view(['GET'])
def current_user(request):
    """
    Determine the current user by their token, and return their data
    """
    
    serializer = UserSerializer(request.user)
    return Response(serializer.data)


class UserList(APIView):
   
    permission_classes = (permissions.AllowAny,)

    def post(self, request, format=None):
        serializer = UserSerializerWithToken(data=request.data)
        if serializer.is_valid():
            serializer.save()
            return Response(serializer.data, status=status.HTTP_201_CREATED)
        return Response(serializer.errors, status=status.HTTP_400_BAD_REQUEST)



def get_file(request):
    
    print("hello ",request)
    print(request.FILES['file'])
    myfile = request.FILES['file']
    fs = FileSystemStorage()
    filename = fs.save(myfile.name, myfile)
    uploaded_file_url = fs.url(filename)
    obj = File.objects.create(image=uploaded_file_url)
    if obj:
        return JsonResponse({"code":200,"msg":"success"})
    else:
        return JsonResponse({"code":500,"msg":"server error"})


class PostView(APIView):
    permission_classes = (permissions.AllowAny,)
    def get(self, request, *args, **kwargs):
        posts = Post.objects.all()
        serializer = PostSerializer(posts, many=True)
        return Response(serializer.data)

    def post(self, request, *args, **kwargs):
        print("hello ",request.data)
        posts_serializer = PostSerializer(data=request.data)
        if posts_serializer.is_valid():
            posts_serializer.save()
            return Response(posts_serializer.data, status=status.HTTP_201_CREATED)
        else:
            print('error', posts_serializer.errors)
            return Response(posts_serializer.errors, status=status.HTTP_400_BAD_REQUEST)