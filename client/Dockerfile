FROM  python:3.8.13-bullseye

ENV PYTHONUNBUFFERED=1

WORKDIR /DIST_PROJECT-MASTER

RUN pip install Django==3.2 django-cors-headers djangorestframework==3.14.0 djangorestframework_jwt==1.11.0

# copy from the current directory of the Dockerfile to /api in the image
COPY . . 

EXPOSE 8000