import { useRouter } from 'next/router';
import React, { useState } from 'react';
import Layout from '../layouts/Layout';
import InventoryImage from '../public/inventory.svg';
import axios from 'axios';
import Image from 'next/image';
import Link from 'next/link';
import { Button, Form, Input, Alert, notification } from 'antd';

const Register: React.FC = () => {
  const [email, setEmail] = useState<string>();
  const [firstName, setFirstName] = useState<string>();
  const [lastName, setLastName] = useState<string>();
  const [password, setPassword] = useState('');
  const [confirmPassword, setConfirmPassword] = useState('');
  const [address, setAddress] = useState<string>();
  const router = useRouter();

  const submit = async (e: any) => {
    e.preventDefault();

    await axios
      .post('http://localhost:8091/api/v1/user/signup', {
        headers: { 'Content-Type': 'application/json' },
        email,
        firstName,
        lastName,
        password,
        confirmPassword,
        address,
      })
      .then(function () {
        <Alert message='Successfully logged in!' type='success' />;
      })
      .catch(function () {
        <Alert message='Something went wrong, Try again!' type='error' />;
      });

    await router.push('/login');
  };

  return (
    // <Layout>
    <div
      style={{
        display: 'flex',
        justifyContent: 'space-evenly',
        alignItems: 'center',
        height: '95vh',
      }}
    >
      <Image src={InventoryImage} alt='image' />

      <div style={{ width: '500px' }}>
        <Form>
          <h1>Register</h1>
          <Form.Item
            name='firstName'
            rules={[
              { required: true, message: 'Please input your first name!' },
            ]}
          >
            <Input
              onChange={(event) => setFirstName(event.target.value)}
              placeholder='First Name'
            />
          </Form.Item>

          <Form.Item
            name='lastName'
            rules={[
              { required: true, message: 'Please input your last name!' },
            ]}
          >
            <Input
              onChange={(event) => setLastName(event.target.value)}
              placeholder='Last Name'
            />
          </Form.Item>

          <Form.Item
            name='email'
            rules={[{ required: true, message: 'Please input your email!' }]}
          >
            <Input
              onChange={(event) => setEmail(event.target.value)}
              placeholder='you@example.com'
            />
          </Form.Item>

          <Form.Item
            name='address'
            rules={[{ required: true, message: 'Please input your address!' }]}
          >
            <Input
              onChange={(event) => setAddress(event.target.value)}
              placeholder='Address'
            />
          </Form.Item>

          <Form.Item
            name='password'
            rules={[{ required: true, message: 'Please input your password!' }]}
          >
            <Input.Password
              onChange={(event) => setPassword(event.target.value)}
              placeholder='At least 8 characters'
            />
          </Form.Item>

          <Form.Item
            name='confirmPassword'
            rules={[
              { required: true, message: 'Please confirm your password!' },
            ]}
          >
            <Input.Password
              onChange={(event) => setConfirmPassword(event.target.value)}
              placeholder='Confirm Password'
            />
          </Form.Item>

          <Form.Item>
            <Button
              type='primary'
              htmlType='submit'
              onClick={submit}
              style={{ width: '100%', marginTop: '15px' }}
            >
              Register
            </Button>
          </Form.Item>

          <p style={{ display: 'flex', justifyContent: 'center' }}>
            Already have an account?{' '}
            <Link href='/login' style={{ marginLeft: '5px' }}>
              Login
            </Link>
          </p>
        </Form>
      </div>
    </div>
    // </Layout>
  );
};

export default Register;
