import React, { Component,useEffect } from 'react';
import axios from 'axios';
import { string } from 'prop-types';

// var array = []

class Upload extends Component {

  state = {
    title: '',
    content: '',
    image: null
  };

  handleChange = (e) => {
    console.log(e.target.files)
    // Trying to version control on the client end
    // console.log(e)
    // let count = 0;
    // array.push(this.state.content);
    // // looping through the items
    // for (let i = 0; i < array.length; i++) {

    //     // check if the character is at that position
    //     if (i === this.state.content) {
    //         count = count +  1;
    //         array.push(this.state.content);
    //     }
    //   }

    this.setState({
      [e.target.id]: e.target.value
    })
    console.log(e.target.id,e.target.value)
  };

  handleImageChange = (e) => {
    console.log(e.target.files[0])
    this.setState({
      image: e.target.files[0],
    })
  };
  handleSubmit = (e) => {

    // Attempt at handling mutiple file versions

    // var urlToFile = "http://localhost:8000/core/posts/" + this.state.content
    // var xhr = new XMLHttpRequest();
    // xhr.open('HEAD', urlToFile, false);
    // xhr.send();

    // console.log("output", xhr.status)
    // if (xhr.status == 200){
    //     this.state.content = this.state.content + 1
    // }

    // loop through the array

    
    e.preventDefault();
    
    // console.log(this.state);
    
    // let count = 0;
    // array.push(this.state.content);
    // // looping through the items
    // for (let i = 0; i < array.length; i++) {

    //     // check if the character is at that position
    //     if (i === this.state.content) {
    //         count = count +  1;
    //         array.push(this.state.content);
    //     }
    
    // }
    // this.state.content = this.state.content + '/' + count
    // console.log(array)
    // console.log(count)
    console.log(this.state.content)


    var url_string = String(this.state.content)

    let form_data = new FormData();
    console.log(this.state.content)
    form_data.append('image', this.state.image, url_string);
    form_data.append('title', this.state.title);
    form_data.append('content', this.state.content);
    console.log(form_data.values())
    let url = 'http://localhost:8000/core/posts/';
    axios.post(url, form_data, {
      headers: {
        'content-type': 'multipart/form-data'
      }
    })
        .then(res => {
          console.log(res.data);
          console.log(res.data.image);
          alert('Your file is stored here http://localhost:8000' + res.data.image)
          useEffect(() => {
            document.h2 = 'Your file as been uploaded';
          });
          
        })
        .catch(err => console.log(err))
  };

  render() {
    return (
    
      <div className="App">
        <form onSubmit={this.handleSubmit}>
          <p>
            <input type="text" placeholder='Title' id='title' value={this.state.title} onChange={this.handleChange} required/>
          </p>
          <p>
            <input type="text" placeholder='URL PATH' id='content' value={this.state.content} onChange={this.handleChange} required/>

          </p>
          <p>
            <input type="file"
                   id="image"
                   onChange={this.handleImageChange} required/>
          </p>
          <input type="submit"/>
        </form>
      </div>
    );
  }
}

export default Upload;