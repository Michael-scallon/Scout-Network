import React, { Component,useEffect } from 'react';
import PropTypes from 'prop-types';
import
class Table extends Component {
    constructor(props) {
        super(props);
        this.state = {
            customers: []
        }
    }
    componentDidMount() {
        fetch("https://raw.githubusercontent.com/aspsnippets/test/master/Customers.json")
        .then(res => res.json())
        .then(
            (customers) => {
                this.setState({ customers: customers });
                console.log(customers)
            },
            (error) => {
                alert(error);
            }
        )
    }
 
    render() {
        return (<table cellPadding="0" cellSpacing="0">
            <thead>
                <tr>
                    <th>CustomerId</th>
                    <th>Name</th>
                    <th>Country</th>
                </tr>
            </thead>
 
            <tbody>
                {this.state.customers.map(customer =>
                    <tr>
                        <td>{customer.CustomerId}</td>
                        <td>{customer.Name}</td>
                        <td>{customer.Country}</td>
                    </tr>
                )}
            </tbody>
        </table>);
    }
}
 
ReactDOM.render(
    <Table />,
    document.getElementById('dvCustomersGrid')
);

export default Table;