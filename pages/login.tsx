import axios from 'axios';
import React, { useState } from 'react';
import Layout from '../layouts/Layout';
import { useRouter } from 'next/router';
import { Button, Form, Input, Alert, message } from 'antd';
import InventoryImage from '../public/inventory.svg';
import Image from 'next/image';
import Link from 'next/link';

const Login: React.FC = () => {
  const router = useRouter();
  const [email, setEmail] = useState<string>();
  const [password, setPassword] = useState('');

  const [messageApi, contextHolder] = message.useMessage();

  const submit = async (e: any) => {
    e.preventDefault();

    await axios
      .post('http://localhost:8091/api/v1/user/login', {
        headers: { 'Content-Type': 'application/json' },
        email,
        password,
      })
      .then(function (response) {
        const token = response.data.token;
        localStorage.setItem('token', token);
        router.push('/success');
        messageApi.info('Successfully logged in!');
      })
      .catch(function (error) {
        messageApi.info('Something went wrong, try again!');
      });
  };

  return (
    <>
      {contextHolder}
      <Layout>
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
              <h1>Welcome Back</h1>
              <Form.Item
                name='email'
                rules={[
                  { required: true, message: 'Please input your email!' },
                ]}
              >
                <Input
                  onChange={(event) => setEmail(event.target.value)}
                  placeholder='you@example.com'
                />
              </Form.Item>

              <Form.Item
                name='password'
                rules={[
                  { required: true, message: 'Please input your email!' },
                ]}
              >
                <Input.Password
                  onChange={(event) => setPassword(event.target.value)}
                  placeholder='At least 8 characters'
                />
              </Form.Item>

              <Form.Item>
                <Button
                  type='primary'
                  htmlType='submit'
                  onClick={submit}
                  style={{ width: '100%', marginTop: '15px' }}
                >
                  Login
                </Button>
              </Form.Item>

              <p style={{ display: 'flex', justifyContent: 'center' }}>
                Don’t you have an account?{' '}
                <Link href='/register' style={{ marginLeft: '5px' }}>
                  Signup
                </Link>
              </p>
            </Form>
          </div>
        </div>
      </Layout>
    </>
  );
};

export default Login;
